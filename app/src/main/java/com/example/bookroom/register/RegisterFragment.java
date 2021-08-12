package com.example.bookroom.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookroom.LoginActivity;
import com.example.bookroom.MainActivity;
import com.example.bookroom.R;
import com.example.bookroom.UserDatabase;
import com.example.bookroom.databinding.FragmentRegisterBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;
    UserDatabase userDatabase;
    List<RegisterUser> registerUserList;
    LoginActivity activity;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.setArguments(args);
        return registerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.executePendingBindings();
        registerViewModel = ViewModelProviders.of(getActivity()).get(RegisterViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setRegisterViewModel(registerViewModel);
        userDatabase = UserDatabase.getInstance(getActivity());
        reloadCapcha();
        activity= (LoginActivity) getActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModel.getUser().observe(activity, new Observer<RegisterUser>() {
            @Override
            public void onChanged(RegisterUser registerUser) {
                if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).getStrEmailAddress())) {
                    Toast.makeText(activity, "Email không được để trống", Toast.LENGTH_LONG).show();
                    binding.etEmail.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).getStrUsername())) {
                    Toast.makeText(activity, "Username không được để trống", Toast.LENGTH_LONG).show();
                    binding.etEmail.requestFocus();
                } else if (!registerUser.isEmailValid()) {
                    Toast.makeText(activity, "Email nhập sai định dạng", Toast.LENGTH_LONG).show();
                    binding.etEmail.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).getStrPassword())) {
                    Toast.makeText(activity, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
                    binding.etPassword.requestFocus();
                } else if (!registerUser.isPasswordLengthGreaterThan5()) {
                    Toast.makeText(activity, "Mật khẩu phải có độ dài tối thiếu 6 kí tự", Toast.LENGTH_LONG).show();
                    binding.etPassword.requestFocus();
                } else if (!registerUser.comparePassword()) {
                    Toast.makeText(activity, "Mật khẩu và xác nhận mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                    binding.etPassword.requestFocus();
                    binding.etPasswordConfirm.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(registerUser).getCapCha())) {
                    Toast.makeText(activity, "Mã capcha không được để trống", Toast.LENGTH_LONG).show();
                    binding.etCapcha.requestFocus();
                } else if (!registerUser.getCapCha().equals(binding.txtCapcha.getText().toString())) {
                    Toast.makeText(activity, "Mã capcha không đúng !", Toast.LENGTH_LONG).show();
                    binding.etCapcha.requestFocus();
                } else {
                    registerUserList=userDatabase.getAll();
                    if(registerUserList.size()==0)
                    {
                        RegisterUser registerUserValue = new RegisterUser(registerUser.getStrUsername(), registerUser.getStrEmailAddress(), registerUser.getStrPassword());
                        userDatabase.insertUser(registerUserValue);
                        binding.btnRegister.requestFocus();
                        Toast.makeText(activity, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();
                        openMainActivity();
                    }
                    else
                    {
                        for (RegisterUser registerUser1 : registerUserList) {
                            if (registerUser1.getStrEmailAddress().equals(registerUser.getStrEmailAddress())) {
                                binding.etEmail.setError("Email bạn nhập đã tồnt tại");
                                binding.etEmail.requestFocus();
                                break;
                            }
                            else
                            {
                                RegisterUser registerUserValue = new RegisterUser(registerUser.getStrUsername(), registerUser.getStrEmailAddress(), registerUser.getStrPassword());
                                userDatabase.insertUser(registerUserValue);
                                binding.btnRegister.requestFocus();
                                Toast.makeText(activity, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();
                                openMainActivity();
                                break;
                            }
                        }
                    }



                }
            }
        });
        binding.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadCapcha();

            }
        });
    }

    private void reloadCapcha() {
        char str = 0;
        ArrayList<String> listRandom = new ArrayList<>();
        Random r = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 50; i++) {
            if (listRandom.size() < 4) {
                str = alphabet.charAt(r.nextInt(alphabet.length()));
                listRandom.add(String.valueOf(str));
            } else
                break;
        }
        binding.txtCapcha.setText(listRandom.get(0) + listRandom.get(1) + listRandom.get(2) + listRandom.get(3));
        Log.e("tag", listRandom.toString());
    }
    private void openMainActivity() {
        startActivity(new Intent(activity, MainActivity.class));
    }
}