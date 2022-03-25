package com.larvata.socialmedia.plugin.login

import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

object FacebookLoginHelper {

    fun login(fragment: Fragment, callbackManager: CallbackManager){
        LoginManager.getInstance()
            .logInWithReadPermissions(
                fragment,
                callbackManager,
                listOf("public_profile", "email")
            )
    }

    fun registerCallback(callbackManager: CallbackManager,
                         onSuccess: (result: LoginResult?) -> Unit,
                         onError: (e: FacebookException) -> Unit,
                         onCancel: () -> Unit){
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onCancel() {
                    onCancel.invoke()
                }

                override fun onError(error: FacebookException) {
                    onError(error)
                }

                override fun onSuccess(result: LoginResult?) {
                    onSuccess(result)
                }
            }
        )
    }

}