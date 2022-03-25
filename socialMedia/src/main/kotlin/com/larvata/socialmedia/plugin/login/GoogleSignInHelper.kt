package com.larvata.socialmedia.plugin.login

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

object GoogleSignInHelper {

    fun login(googleSignInClient: GoogleSignInClient, resultLauncher: ActivityResultLauncher<Intent>){
        val intent = googleSignInClient.signInIntent
        resultLauncher.launch(intent)
    }

    fun processResult(completedTask: Task<GoogleSignInAccount>): GoogleSignInAccount {
        return try {
            completedTask.getResult(ApiException::class.java)
        }catch (e: ApiException) {
            throw e
        }
    }

}