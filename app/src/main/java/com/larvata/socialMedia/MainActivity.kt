package com.larvata.socialMedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.larvata.socialMedia.databinding.ActivityMainBinding
import com.larvata.socialmedia.plugin.SocialMediaFuncs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        binding.apply {
            fbTestBtn.setOnClickListener {
                SocialMediaFuncs.openFacebook(this@MainActivity, "531419738790357")
            }
            igTestBtn.setOnClickListener {
                SocialMediaFuncs.openInstagram(this@MainActivity, "taipei101_official")
            }
        }
    }
}