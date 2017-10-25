package bg.devlabs.flyingfab

import android.annotation.SuppressLint
import android.support.design.widget.AppBarLayout
import bg.devlabs.flyingfab.AppBarStateChangeListener.AppBarState


/**
 * Created by Simona Stoyanova on 10/19/17.
 * simona@devlabs.bg
 * A listener which is called when the appbar state changes
 * The possible states are defined in
 *
 * @see AppBarState
 */

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    private var isButtonTop = true

    private var lastState = AppBarState.COLLAPSED

    private enum class AppBarState {
        EXPANDED,
        COLLAPSED,
        MOVING
    }

    @SuppressLint("LogConditional")
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        when {
            verticalOffset == 0 -> {
                /* new state is EXPANDED */
                if (lastState == AppBarState.MOVING) {
                    moveButtonTop()
                    isButtonTop = true
                }
                lastState = AppBarState.EXPANDED
            }
            Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                /* new state is COLLAPSED */
                if (lastState == AppBarState.MOVING) {
                    moveButtonDown()
                    isButtonTop = false
                }
                lastState = AppBarState.COLLAPSED
            }
            else -> {
                /* new state is MOVING */
                if (lastState != AppBarState.COLLAPSED &&
                        Math.abs(verticalOffset) < appBarLayout.totalScrollRange
                        && isButtonTop) {
                    moveButtonAccordingToOffset(verticalOffset)
                }
                lastState = AppBarState.MOVING
            }
        }
    }

    protected abstract fun moveButtonAccordingToOffset(verticalOffset: Int)

    protected abstract fun moveButtonTop()

    protected abstract fun moveButtonDown()
}