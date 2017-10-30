package bg.devlabs.flying_fab_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import bg.devlabs.flyingfab.FlyingFab
import kotlinx.android.synthetic.main.activity_animated_fab.*

/**
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 *
 * An example activity which illustrates the usage of FlyingFab capabilities.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_fab)

        FlyingFab()
                .interpolatorUp(AccelerateDecelerateInterpolator())
                .interpolatorDown(BounceInterpolator())
                .animationDurationUp(500L)
                .animationDurationDown(1200L)
                .setup(app_bar_layout, fab_1)

        //setup the rest of the screen
        toolbar.title = getString(R.string.kittens)
    }
}
