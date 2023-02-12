package com.example.memesapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var currentimgurl  : String? = null
    private lateinit var memeimageView:ImageView
    private lateinit var btnshare:Button
    private lateinit var btnnext : Button
  /*  private lateinit var progressBar : ProgressBar*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnshare = findViewById(R.id.btnshare)
        btnnext = findViewById(R.id.btnext)
        memeimageView=findViewById(R.id.memeimageView )
        loadmeme()


        btnshare.setOnClickListener{
            val intent =Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"HEY check this out $currentimgurl")
            val chooser=Intent.createChooser(intent, "share this meme using...")
            startActivity(chooser)
        }
        btnnext.setOnClickListener{
            loadmeme()
        }
    }

   private fun loadmeme(){
      /* progressBar.visibility=View.VISIBLE*/
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
       currentimgurl  = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,  currentimgurl ,null,
            Response.Listener { response ->
                currentimgurl = response.getString("url")
                Glide.with(this).load( currentimgurl ).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        /*progressBar.visibility= View.GONE*/
                        return false

                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        /*progressBar.visibility= View.GONE*/
                        return false

                    }
                }).into(memeimageView)

            },
            Response.ErrorListener {  })
        queue.add(JsonObjectRequest)
    }
}