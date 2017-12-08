package jljt.wangs.com.latte_core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/8.
 */

public class RequestCallbacks implements Callback<String>{
    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure) {
        this.Request = request;
        this.Success = success;
        this.Error = error;
        this.Failure = failure;
    }

    private final IRequest Request;
    private  final ISuccess Success;
    private  final IError Error;
    private  final IFailure Failure;

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){//请求成功
            if(call.isExecuted()){
                if(Success!=null){
                    Success.onSuccess(response.body());
                }
            }
        }
        else {
            if(Error!=null){
                 Error.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
      if(Failure!=null){
          Failure.onFailure();
      }
      if(Request!=null){
          Request.onRequestEnd();
      }
    }
}
