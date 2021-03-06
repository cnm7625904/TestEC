package jljt.wangs.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import jljt.wangs.com.latte.ec.R;
import jljt.wangs.com.latte.ec.R2;
import jljt.wangs.com.latte_core.delegates.LatteDelegate;
import jljt.wangs.com.latte_core.net.RestClient;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import jljt.wangs.com.latte_core.util.toast.ToastUtils;

/**
 * Created by Administrator on 2017/12/18.
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName=null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail=null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone=null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword=null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword=null;
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        //信息正确，可以提交表单
        if(checkForm()){
//            RestClient.builder()
//                    .url("sigb_up")
//                    .params("","")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    }).build()
//                    .post();
//            Toast.makeText(getContext(),"验证通过",Toast.LENGTH_SHORT).show();
            ToastUtils.toastLong(getContext(),"验证通过");
        }
    }
    private boolean checkForm(){
        final String name=mName.getText().toString();
        final String email=mEmail.getText().toString();
        final String phone=mPhone.getText().toString();
        final String password=mPassword.getText().toString();
        final String rePassword=mRePassword.getText().toString();
        boolean isPass=true;//是否通过

        if(name.isEmpty()){
            mName.setError("请输入姓名");
            isPass=false;
        }else {
            mName.setError(null);
            isPass=true;
        }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass=false;
        }else {
            mEmail.setError(null);
        }
        if(phone.isEmpty()||phone.length()!=11){
            mPhone.setError("手机号码错误");
            isPass=false;
        }else {
            mPhone.setError(null);
        }
        if(password.isEmpty()||password.length()<6){
            mPassword.setError("请填写至少6位数密码");
            isPass=false;
        }else {
            mPassword.setError(null);
        }
        if(rePassword.isEmpty()||rePassword.length()<6||!(rePassword.equals(password))){
            mRePassword.setError("密码验证错误");
            isPass=false;
        }else {
            mRePassword.setError(null);
        }
             return isPass;

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
