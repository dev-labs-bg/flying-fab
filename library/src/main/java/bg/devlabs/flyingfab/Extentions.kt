package bg.devlabs.flyingfab

import android.view.View
import android.view.ViewPropertyAnimator

/**
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 */
fun View.animation(func: ViewPropertyAnimator.() -> Unit) {
    this.animate().apply {
        func()
    }
}