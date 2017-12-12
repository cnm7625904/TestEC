package jljt.wangs.com.latte_core.net;

import android.content.Context;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import jljt.wangs.com.latte_core.net.callback.RequestCallbacks;
import jljt.wangs.com.latte_core.net.download.DownloadHandler;
import jljt.wangs.com.latte_core.ui.LatteLoader;
import jljt.wangs.com.latte_core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/12/8.
 * 请求具体实现类
 * 初值赋值为null,养成一个好习惯,C语言中指针没有赋值就是随机指针
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAIRLURE;
    private final   RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;//下载路径
    private final String EXTENSION;//后缀名
    private final String NAME;//完整名字
    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest requset,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context,
                      File file,
                      String download_Dir,
                      String extension,
                      String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = requset;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAIRLURE = failure;
        this.BODY = body;
        this.LOADER_STYLE=loaderStyle;
        this.CONTEXT=context;
        this.FILE=file;
        this.DOWNLOAD_DIR=download_Dir;
        this.EXTENSION=extension;
        this.NAME=name;
    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call=null;
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case POST_ROW://POST原始数据
                call=service.postRow(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case PUT_ROW:
                call=service.putRow(URL,BODY);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call=RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAIRLURE,
                LOADER_STYLE
        );
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    /**
     * 如果POST的是原始数据，参数一定要是空的
     */
    public final void post(){
        if(BODY!=null){
            request(HttpMethod.POST);
        }
        else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_ROW);
        }
    }
    public final void put(){
        if(BODY!=null){
            request((HttpMethod.PUT));
        }
        else if(!PARAMS.isEmpty()){
            throw new RuntimeException("params must be null!");
        }
        request(HttpMethod.POST_ROW);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }
    public final void download(){
      new DownloadHandler(URL,REQUEST,SUCCESS,ERROR,FAIRLURE,DOWNLOAD_DIR,EXTENSION,NAME).handleDownload();
    }
}
