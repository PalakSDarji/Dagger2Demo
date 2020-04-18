package com.feedr.blog.dagger2demo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks


class PhoneAuthActivity : AppCompatActivity(), View.OnClickListener{


    private val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"

    private val STATE_INITIALIZED = 1
    private val STATE_CODE_SENT = 2
    private val STATE_VERIFY_FAILED = 3
    private val STATE_VERIFY_SUCCESS = 4
    private val STATE_SIGNIN_FAILED = 5
    private val STATE_SIGNIN_SUCCESS = 6

    private lateinit var mAuth : FirebaseAuth
    private var mVerificationInProgress = false
    private var mVerificationId: String? = null

    private var mResendToken: ForceResendingToken? = null
    private lateinit var mCallbacks: OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        mAuth = FirebaseAuth.getInstance()
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(this@PhoneAuthActivity,"Verified",Toast.LENGTH_LONG).show()
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(this@PhoneAuthActivity,"Verification failed",Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)

        // [START_EXCLUDE]
        if (mVerificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(mPhoneNumberField.getText().toString())
        }
        // [END_EXCLUDE]
    }

    override fun onClick(v: View?) {

    }

    companion object {
        private val TAG = PhoneAuthActivity::class.simpleName
    }
}
