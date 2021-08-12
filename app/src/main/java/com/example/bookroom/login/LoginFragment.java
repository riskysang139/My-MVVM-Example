package com.example.bookroom.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookroom.Common.FragmentNavigate;
import com.example.bookroom.LoginActivity;
import com.example.bookroom.MainActivity;
import com.example.bookroom.databinding.FragmentLoginBinding;
import com.example.bookroom.register.RegisterFragment;
import com.example.bookroom.register.RegisterUser;
import com.example.bookroom.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    private UserDatabase userDatabase;
    private LoginActivity mainActivity;
    private List<RegisterUser> usersList;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        {
            binding = FragmentLoginBinding.inflate(inflater, container, false);
            mainActivity = (LoginActivity) getActivity();
            binding.executePendingBindings();
            loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
            binding.setLifecycleOwner(getActivity());
            binding.setLoginViewModel(loginViewModel);
            userDatabase = UserDatabase.getInstance(getActivity());
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel.getUser().observe(getActivity(), new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                    binding.etEmail.setError("Email không được để trống");
                    binding.etEmail.requestFocus();
                } else if (!loginUser.isEmailValid()) {
                    binding.etEmail.setError("Email nhập sai định dạng");
                    binding.etEmail.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                    binding.etPassword.setError("Mật khẩu không được để trống");
                    binding.etPassword.requestFocus();
                } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.etPassword.setError("Mật khẩu phải có độ dài tối thiếu 6 kí tự");
                    binding.etPassword.requestFocus();
                } else {
                    loginViewModel.getAllUser().observe(getActivity(), new Observer<List<RegisterUser>>() {
                        @Override
                        public void onChanged(List<RegisterUser> registerUsers) {
                            usersList=new ArrayList<>();
                            usersList=registerUsers;
                            for (RegisterUser users : usersList) {

                                if (loginUser.getStrEmailAddress().equals(users.getStrEmailAddress()) &&
                                        loginUser.getStrPassword().toString().equals(users.getStrPassword())) {
                                    Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    openMainActivity();
                                    binding.btnLogin.requestFocus();
                                    break;
                                }
                                else {
                                    Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }

            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment = RegisterFragment.newInstance();
                FragmentNavigate.addNewFragment(getActivity(), registerFragment);
            }
        });
    }

    private void openMainActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
