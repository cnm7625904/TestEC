package jljt.wangs.com.latte_core.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import jljt.wangs.com.latte_core.net.RestCreator;
import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/12.
 */

public class DownloadHandler {
    private final String URL;
    private final WeakHashMap<String,Object> PARAMS= RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAIRLURE;

    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IError error,
                           IFailure failure,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAIRLURE = failure;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    private final String DOWNLOAD_DIR;//下载路径
    private final String EXTENSION;//后缀名
    private final String NAME;//完整名字
    public final void handleDownload(){
        //请求不为空，就开始下载
      if(REQUEST!=null){
           REQUEST.onRequestStart();
      }
      RestCreator.getRestService().download(URL,PARAMS)
              .enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        final ResponseBody responseBody=response.body();
                        final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS,ERROR,FAIRLURE);
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);//以线程池的方式执行
                        //判断是否下载完成，否则文件下载不全
                        if (task.isCancelled()){
                            if(REQUEST!=null){
                                REQUEST.onRequestEnd(); 
                            }
                        }else {
                            if(ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }
                  }

                  @Override
                  public void onFailure(Call<ResponseBody> call, Throwable t) {
                      if(FAIRLURE!=null){
                          FAIRLURE.onFailure();
                      }
                  }
              });
    }
}
