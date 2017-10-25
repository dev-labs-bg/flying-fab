package bg.devlabs.flying_fab_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AccelerateDecelerateInterpolator
import bg.devlabs.flyingfab.FlyingFab
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_animated_fab.*
import kotlinx.android.synthetic.main.kittens_content.*

class MainActivity : AppCompatActivity() {
    private var adapter = ImageAdapter()
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
        setupFirstRecyclerView()
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

    private fun setupFirstRecyclerView() {
        adapter.images = kittenImages
        recycler_view?.adapter = adapter
        recycler_view?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
    }

    private val kittenImages = arrayOf(
            "http://cdn.earthporm.com/wp-content/uploads/2015/07/cutest-sleeping-kitties-ever-106__605.jpg",
            "http://www.petwave.com/~/media/Images/Center/Care-and-Nutrition/Cat/Kittensv2/Kitten-3.ashx",
            "https://www.pets4homes.co.uk/images/articles/3448/six-health-essentials-that-you-should-monitor-in-your-kittens-first-few-weeks-of-life-56a6114cb4003.jpg",
            "https://www.pets4homes.co.uk/images/articles/2461/health-personality-what-to-look-for-in-a-kitten-54ad2c85a8441.jpg",
            "http://paws-and-effect.com/wp-content/uploads/2017/04/PE-kitten.png",
            "https://www.pets4homes.co.uk/images/articles/1921/how-to-choose-a-kitten-that-will-suit-you-your-lifestyle-53d23b8c223c3.jpg",
            "http://cdn.playbuzz.com/cdn/8b2455a1-b061-4ffa-910f-4d1419c35ba1/9e5ed9d8-9477-48dd-9cd2-e563d82ca00f.jpg",
            "https://www.pets4homes.co.uk/images/articles/3539/how-a-kittens-personality-is-formed-before-they-are-old-enough-to-go-to-their-new-homes-572b058644092.jpg",
            "http://eskipaper.com/images/kitten-nature-1.jpg"
    )
}
