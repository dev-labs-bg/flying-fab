package bg.devlabs.flyingfab

import android.animation.TimeInterpolator
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

@Suppress("unused")
/**
 * Copyright (c) 2017 Simona Stoyanova
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Created by Simona Stoyanova on 10/25/17.
 * simona@devlabs.bg
 * Handles AppBarStateChanges and animates the Floating action button
 * according to the position and the State of the AppBarLayout
 *
 * Can be created like FabAnimationHandler fabh = new FabAnimationHandler()
 * Should call setup(appBarLayout: AppBarLayout, fab: FloatingActionButton)
 * in order for the view to be animated
 *
 * @see AppBarStateChangeListener.AppBarState
 */
class FlyingFab {
    private val DEFAULT_TIME_INTERPOLATOR = AccelerateDecelerateInterpolator()
    private val DEFAULT_ANIMATION_DURATION = 800L
    private var interpolator: TimeInterpolator = DEFAULT_TIME_INTERPOLATOR
    private var animationDuration: Long = DEFAULT_ANIMATION_DURATION

    @Suppress("unused", "RedundantVisibilityModifier")
    public fun setup(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
        val mListener = object : AppBarStateChangeListener() {

            override fun onCollapsing(verticalOffset: Int) {
                /**
                 * The button needs to move alongside with the collapsing appbar
                 * relatively to the current verticalOffset of the appbar.
                 */
                val appBarHeight = appBarLayout.height
                val fabHeight = fab.height
                val translateNew = appBarHeight + verticalOffset - fabHeight / 2f
                animateY(fab, translateNew, 0, interpolator)
            }

            override fun onCollapsed() {
                /**
                 * The button needs to move to the bottom right part of the expanded appbar
                 * and become "anchored" to the collapsing  appbar.
                 */
                val appBarHeight = appBarLayout.height
                val fabHeight = fab.height
                val translateNew = appBarHeight - fabHeight / 2f
                animateY(fab, translateNew, animationDuration, interpolator)
            }

            override fun onExpanded() {
                /**
                 * The button needs to move to the bottom right part of the screen,
                 * with 16 dp margin.
                 */
                val context = appBarLayout.context
                val heightPixels = context.resources.displayMetrics.heightPixels
                val sixteenDpToPixel = convertDpToPixel(16f, context)
                val translateNew = heightPixels - fab.height - sixteenDpToPixel
                animateY(fab, translateNew, animationDuration, interpolator)
            }
        }
        appBarLayout.addOnOffsetChangedListener(mListener)
    }

    /**
     * Sets the interpolator for the underlying animator that animates the requested properties.
     * By default, the animator uses the [FlyingFab.DEFAULT_TIME_INTERPOLATOR] interpolator.
     * Calling this method will cause the declared interpolator to be used instead.
     *
     * @param interpolator The TimeInterpolator to be used for ensuing property animations. A value
     * of <code>null</code> will result in [FlyingFab.DEFAULT_TIME_INTERPOLATOR] interpolation.
     * @return This object, allowing calls to methods in this class to be chained.
     *
     * Known Indirect Subclasses of TimeInterpolator:
     * AccelerateDecelerateInterpolator,AccelerateInterpolator,
     * AnticipateInterpolator,AnticipateOvershootInterpolator,
     * BaseInterpolator,BounceInterpolator,CycleInterpolator,
     * DecelerateInterpolator,FastOutLinearInInterpolator,
     * FastOutSlowInInterpolator,Interpolator,LinearInterpolator,
     * LinearOutSlowInInterpolator,OvershootInterpolator,PathInterpolator
     */
    @Suppress("unused", "RedundantVisibilityModifier")
    public fun interpolator(interpolator: TimeInterpolator?): FlyingFab {
        this.interpolator = interpolator ?: DEFAULT_TIME_INTERPOLATOR
        return this
    }

    /**
     * Sets the duration of the animation in which the button is animated from top to bottom and reverse.
     * By default, the duration is [FlyingFab.DEFAULT_ANIMATION_DURATION].
     * Calling this method will cause the declared duration to be used instead.
     *
     * @param animationDuration The animation duration to be used for ensuing property animations. A value
     * of <code>null</code> will result in [FlyingFab.DEFAULT_ANIMATION_DURATION].
     * @return This object, allowing calls to methods in this class to be chained.
     *
     */
    @Suppress("unused", "RedundantVisibilityModifier")
    public fun animationDuration(animationDuration: Long?): FlyingFab {
        this.animationDuration = animationDuration ?: DEFAULT_ANIMATION_DURATION
        return this
    }

    /**
     * Applies the passed parameters to the view's animation.
     *
     * @param view The view to which the animation is applied.
     * @param translateToY The y value to be animated to.
     *  @see android.view.ViewPropertyAnimator#y(float)
     * @param aDuration the duration of the animation.
     * @param interpolator used only if the animation is not an instant animation (aDuration = 0).
     */
    private fun animateY(view: View, translateToY: Float, aDuration: Long, interpolator: TimeInterpolator) {
        view.animation {
            y(translateToY)
            duration = aDuration
            if (duration > 0) {
                this.interpolator = interpolator
            }
        }
    }
}