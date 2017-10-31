FlyingFab
==============

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [ ![Download](https://api.bintray.com/packages/sstoyanova/flyingfab/flying-fab/images/download.svg?version=0.0.3) ](https://bintray.com/sstoyanova/flyingfab/flying-fab/0.0.3/link)

FlyingFab is a small library for animating [FAB](https://developer.android.com/reference/android/support/design/widget/FloatingActionButton.html) as if it is "flying", written in Kotlin.

It is created for the specific case in which an [AppBarLayout](https://developer.android.com/reference/android/support/design/widget/AppBarLayout.html) containing [CollapsingToolbarLayout](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout.html) is present alongside with the FAB. 


It listens for AppBarLayout.OnOffsetChangedListener and implements FAB animation depending on its state - Collapsed, Expanded or Collapsing.

The interface is pretty simple to use. You just need to pass the AppBarLayout and the FAB after they are inflated.

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_fab)
        FlyingFab()
                .interpolator(AccelerateDecelerateInterpolator()) //optional
                .animationDuration(800L) //optional
                .setup(app_bar_layout, fab_1)}
```

FlyingFab includes a flexible API that allows developers to change the animation duration and TimeInterpolator. You just need to use the two methods below:

 ```
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
```


Download
===========
The download is still in progress.

Samples
========
Code samples are available in the app module.
Here's a sample video:

![Sample Video](https://github.com/sstoyanova/flying-fab/blob/master/FlyingFabExample.gif)


Getting Help
========
To report a specific problem or feature request, open a new issue on Github. For questions, suggestions, or anything else you can send me an email.

Author
========
Simona Stoyanova - [@sstoyanova](https://github.com/sstoyanova) on GitHub, @SimonaStoyanova on Twitter

License
========
FlyingFab is released under the [MIT License](https://gitlab.com/SimonaStoyanova/flying-fab/blob/master/LICENSE) file for details.

Disclaimer
========
As this animation is not recognized as a best practice I don't advice you to use it in your application.
