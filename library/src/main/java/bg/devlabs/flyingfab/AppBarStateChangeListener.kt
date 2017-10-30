package bg.devlabs.flyingfab

import android.annotation.SuppressLint
import android.support.design.widget.AppBarLayout
import bg.devlabs.flyingfab.AppBarStateChangeListener.AppBarState


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
                    onCollapsed()
                    isButtonTop = true
                }
                lastState = AppBarState.EXPANDED
            }
            Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                /* new state is COLLAPSED */
                if (lastState == AppBarState.MOVING) {
                    onExpanded()
                    isButtonTop = false
                }
                lastState = AppBarState.COLLAPSED
            }
            else -> {
                /* new state is MOVING */
                if (lastState != AppBarState.COLLAPSED &&
                        Math.abs(verticalOffset) < appBarLayout.totalScrollRange
                        && isButtonTop) {
                    onCollapsing(verticalOffset)
                } else {
                    onExpanding(verticalOffset)
                }
                lastState = AppBarState.MOVING
            }
        }
    }

    /**
     * Called multiple times while the appbar is expanding.
     */
    abstract fun onExpanding(verticalOffset: Int)

    /**
     * Called multiple times while the appbar is collapsing.
     */
    protected abstract fun onCollapsing(verticalOffset: Int)

    /**
     * Called once the appbar is fully collapsed.
     */
    protected abstract fun onCollapsed()

    /**
     * Called once the appbar is fully expanded.
     */
    protected abstract fun onExpanded()
}