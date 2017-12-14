package jljt.wangs.com.latte_core.net;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import jljt.wangs.com.latte_core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/12/8.
 * final修饰不能修改
 * 因为每次都要创建所以修改为全局变量
 */

public class RestClientBuilder {
    private  String mUrl=null;
    private static final  Map<String,Object> PARAMS=RestCreator.getParams();
    private  IRequest mIRequest=null;
    private  ISuccess mISuccess=null;
    private  IError mIError=null;
    private  IFailure mIFailure=null;
    private RequestBody mBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;
    private File mFile=null;
    private  String mDownloadDir;//下载路径
    private  String mExtension;//后缀名
    private  String mName;//完整名字

    public RestClientBuilder() {
    }
    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder params(HashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 存放目录
     * @param dir
     * @return
     */
    public final RestClientBuilder dir(String dir){
        this.mDownloadDir=dir;
        return this;
    }

    /**
     * 后缀名
     * @param extension
     * @return
     */
    public final RestClientBuilder extension(String extension){
        this.mExtension=extension;
        return this;
    }
    /**
     * 存放完整名字
     * @param name
     * @return
     */
    public final RestClientBuilder name(String name){
        this.mName=name;
        return this;
    }
    /**
     * 重载
     */
    public final RestClientBuilder params(String key,Object value){

        PARAMS.put(key,value);
        return this;
    }
    public final RestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }
    public final RestClientBuilder onRequst(IRequest iRequest){
        this.mIRequest=iRequest;
        return this;
    }
    public final RestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }
    public final RestClientBuilder file(String file){
        this.mFile=new File(file);
        return this;
    }
    //    /**
//     * 检查
//     */
//    private Map<String,Object> cheackParams(){
//        if(PARAMS==null){
//            return  new WeakHashMap<>();
//        }
//        return PARAMS;
//    }
    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mLoaderStyle=loaderStyle;
        this.mContext=context;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext=context;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIError,mIFailure,mBody,mLoaderStyle,mContext,mFile,mDownloadDir,mExtension,mName);
    }
}
