package bg.devlabs.flyingfab

import android.content.Context
import android.util.DisplayMetrics

/**
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 */

fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
