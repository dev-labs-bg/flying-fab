FlyingFab
==============

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [ ![Download](https://api.bintray.com/packages/sstoyanova/flyingfab/flying-fab/images/download.svg?version=0.0.5) ](https://bintray.com/sstoyanova/flyingfab/flying-fab/0.0.5/link)[![Kotlin](https://img.shields.io/badge/kotlin-1.1.51-blue.svg)](http://kotlinlang.org)

FlyingFab is a small library for animating [FAB](https://developer.android.com/reference/android/support/design/widget/FloatingActionButton.html) as if it is "flying", written in Kotlin.

It is created for the specific case in which an [AppBarLayout](https://developer.android.com/reference/android/support/design/widget/AppBarLayout.html) containing [CollapsingToolbarLayout](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout.html) is present alongside with the FAB. 


It listens for AppBarLayout.OnOffsetChangedListener and implements FAB animation depending on its state - Collapsed, Expanded or Collapsing.

The interface is pretty simple to use. You just need to pass the AppBarLayout and the FAB after they are inflated.

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_fab)
        FlyingFab()
                .interpolatorUp(AccelerateDecelerateInterpolator())
                .interpolatorDown(BounceInterpolator())
                .animationDurationUp(500L)
                .animationDurationDown(1200L)
                .setup(app_bar_layout, fab_1)
        //...
}
```

FlyingFab includes a flexible API that allows developers to change the animation duration and TimeInterpolator for the up or/and down animations. You just need to use the methods below:

```
public fun interpolatorUp(interpolator: TimeInterpolator?): FlyingFab
public fun interpolatorDown(interpolator: TimeInterpolator?): FlyingFab
public fun animationDurationUp(animationDuration: Long?): FlyingFab
public fun animationDurationDown(animationDuration: Long?): FlyingFab

```

Download
===========
Grab via Gradle:
```
compile 'compile 'bg.devlabs.flyingfab:flying-fab:<latest version>'
 ```
or Maven:
```
<dependency>
  <groupId>bg.devlabs.flyingfab</groupId>
  <artifactId>flying-fab</artifactId>
  <version><latest version></version>
  <type>pom</type>
</dependency> 
```
 
Requires the following two libraries to be imported:
```
'org.jetbrains.kotlin:kotlin-stdlib-jre7:1.1.51'
'com.android.support:design:26.1.0'
```
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
Simona Stoyanova - [@sstoyanova](https://github.com/sstoyanova) on GitHub, [@SimonaStoyanova](https://twitter.com/SimonaStoyanova) on Twitter

License
========
FlyingFab is released under the [MIT License](https://gitlab.com/SimonaStoyanova/flying-fab/blob/master/LICENSE).

Disclaimer
========
As this animation is not recognized as a best practice I don't advice you to use it in your application.
