# Android Search Interface

<a name="version"></a>
[ ![Download](https://api.bintray.com/packages/edsilfer/maven/user-onboarding/images/download.svg) ](https://bintray.com/edsilfer/maven/user-onboarding/_latestVersion) - **MIN API VERSION: 16**

<a name="summary">
## Summary
1. [Introduction](#introduction) 
2. [How does it work?](#howdoesitwork) 
   * [Step 01](#step1) 
   * [Step 02](#step2) 
   * [Step 03](#step3) 
3. [Special Thanks](#special-thanks) 
4. [License](#license) 

<a name="introduction" />
## Introduction
> User onboarding is the process of improving a person's success with a product or service. This term is often used in reference to software products, and it can be done in a manual or automated way.

*Source: https://en.wikipedia.org/wiki/User_onboarding*

<a name="showcase" />
### SHOWCASE

<p align="center">
  <img src="showcase/onboarding_demo.gif" align="center" width=250>
  <br /><br />
  <i><b>Figure 01:</b> Android User OnBoarding library on action</i>
</p>
<a name="howdoesitwork" />
## How does it work?
Android List Manager was developed using [Kotlin language](https://kotlinlang.org/). Kotlin is free to use and owned by [Jet Brains](https://www.jetbrains.com/). It adds a lot of cool features, boosting your productiveness while keeping everythying **100% compatible with Java.** 

_For details about technical implementation of this library please refer to the source code._

<a name="step1" />
#### Step 01: import the module

```gradle
compile 'br.com.edsilfer.android:user-onboarding:1.0.0'
```


<a name="step2" />
#### Step 02: Fill in a OnBoardingTheme Object:

```kotlin
class OnBoardingTheme : Serializable {
    var bottomPanelColors = BottomPanelColors()
    var pages = listOf<Page>()
}

data class Page(
        var background: Int,
        var header: Int,
        var headerStyle: Text,
        var image: Int,
        var subHeader1: Int,
        var subHeader1Style: Text,
        var subHeader2: Int,
        var subHeader2Style: Text,
        var customLayoutResource: Int // Optional parameter. Do not forget to use same view ids if you set your own layout. 
) : Serializable {}

class BottomPanelColors(
        var indicatorActiveColor: Int = Color.parseColor("#FFFFFF"),
        var indicatorInactiveColor: Int = Color.parseColor("#FFFFFF"),
        var skipColor: Int = Color.parseColor("#FFFFFF"),
        var nextColor: Int = Color.parseColor("#FFFFFF"),
        var finishColor: Int = Color.parseColor("#FFFFFF"),
        var dividerColor: Int = Color.parseColor("#FFFFFF")
) : Serializable {}
```

*Each page added on pages represents a Page to be place as part of the ViewPager*<br />
*You can use different colors for bottom panel via BottomPanelColor class*<br />

<a name="step3" />
#### Step 03: Launch ActivityUserOnBoarding:

```kotlin
val pages = arrayListOf<Page>()
pages.add(getPage1())
pages.add(getPage2())
pages.add(getPage3())
pages.add(getPage4())

val theme = OnBoardingTheme()
theme.pages = pages
theme.panelColor = getBottomPanelColors()
theme.bottomPanelLayoutResource = R.layout.custom_bottom_panel // You can set your own layout for bottom panel 

val intent = Intent(this, ActivityUserOnBoarding::class.java)
intent.putExtra(ActivityUserOnBoarding.ARG_ONBOARDING_THEME, theme)
startActivity(intent)
```

<a name="step4" />
#### Step 04: Check sample app for more details!

<a name="special-thanks" />
## Special Thanks
 - This library was built based on [this](http://blog.grafixartist.com/onboarding-android-viewpager-google-way/) nice tutorial posted by Suleiman on Grafix Artist.
 - User [Josef Hruška](https://github.com/JosefHruska) for improving the versatility of the lib;

<a name="license" />
## License
Copyright 2016 Edgar da Silva Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
