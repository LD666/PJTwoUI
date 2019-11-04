package com.myfirstapplication.pjtwoui.activity

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.myfirstapplication.pjtwoui.R

class SPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp)


        var lottie: LottieAnimationView = findViewById(R.id.my_lottie)

        lottie.repeatCount = 1
        lottie.addAnimatorListener(object: Animator.AnimatorListener{

            override fun onAnimationRepeat(animation: Animator?) {

            }


            override fun onAnimationEnd(animation: Animator?) {
                var myIntent = Intent(this@SPActivity, LoginActivity::class.java)
                startActivity(myIntent)
            }


            override fun onAnimationCancel(animation: Animator?) {

            }


            override fun onAnimationStart(animation: Animator?) {

            }


        })

    }
}
