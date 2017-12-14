package jljt.wangs.com.latte_core.net.callback;

import android.os.Handler;

import jljt.wangs.com.latte_core.ui.loader.LatteLoader;
import jljt.wangs.com.latte_core.ui.loader.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/8.
 */

public class RequestCallbacks implements Callback<String>{
    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE=loaderStyle;
    }

    private final IRequest REQUEST;
    private  final ISuccess SUCCESS;
    private  final IError ERROR;
    private  final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    //Handler尽量声明称static类型,可减少内存泄漏
    private static final Handler HANDLER=new Handler();

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){//请求成功
            if(call.isExecuted()){
                if(SUCCESS !=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }
        else {
            if(ERROR !=null){
                 ERROR.onError(response.code(),response.message());
            }
        }
        if(LOADER_STYLE!=null){
           HANDLER.postDelayed(new Runnable() {
               @Override
               public void run() {
                   LatteLoader.stopLoading();
               }
           },2000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
      if(FAILURE !=null){
          FAILURE.onFailure();
      }
      if(REQUEST !=null){
          REQUEST.onRequestEnd();
      }
    }
}
