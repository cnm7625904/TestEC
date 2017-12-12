package jljt.wangs.com.latte_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import jljt.wangs.com.latte_core.app.Latte;
import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import jljt.wangs.com.latte_core.util.file.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/12.
 * 异步任务
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public SaveFileTask(IRequest request, ISuccess success, IError error, IFailure failure) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir= (String) params[0];//下载路径
        String extension= (String) params[1];//后缀名
        final ResponseBody body= (ResponseBody) params[2];
        final String name= (String) params[3];
        final InputStream inputStream=body.byteStream();
        if(downloadDir==null||downloadDir.equals("")){
            downloadDir="down_loads";
        }
        if(extension==null||extension.equals("")){
            extension="";
        }
        if(name==null){
            return FileUtil.writeToDisk(inputStream,downloadDir,extension.toUpperCase(),extension);
        }
        else {
            return FileUtil.writeToDisk(inputStream,downloadDir,name);
        }
    }
    //执行完异步执行此方法
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }
    //自动安装apk
    private void autoInstallApk(File file){
       if(FileUtil.getExtension(file.getPath()).equals("apk")){
           final Intent install=new Intent();//发起意图
           install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//新建栈
           install.setAction(Intent.ACTION_VIEW);
           install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
           Latte.getApplicationContext().startActivity(install);
       }
    }
}
