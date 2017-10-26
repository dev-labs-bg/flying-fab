package bg.devlabs.flying_fab_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateDecelerateInterpolator
import bg.devlabs.flyingfab.FlyingFab
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_animated_fab.*

/**
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 *
 * An example activity which illustrates the usage of FlyingFab capabilities.
 */

class MainActivity : AppCompatActivity() {
    private val imageUrl = "https://www.carmaa-petadoption.com/wp-content/uploads/" +
            "Fostering-Kittens-Header.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_fab)

        FlyingFab()
                .interpolator(AccelerateDecelerateInterpolator())
                .animationDuration(800L)
                .setup(app_bar_layout, fab_1)

        //setup the rest of the screen
        toolbar.title = getString(R.string.kittens)
        loadImage()
    }

    private fun loadImage() {
        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
        Glide.with(this)
                .load(imageUrl)
                .apply(options)
                .into(cover_image_view)
    }
}
