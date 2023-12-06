# AdventCalendarApp

Advent Calendar App in Kotlin.
Used technology, libs: 
- Jetpack Compose
- Retrofit2
- Room
- Pager
- Hilt

Rest Api of the Images:
https://app.zenserp.com
For run the app on your mobile device/emulator you have to registrer on the https://app.zenserp.com page, and replace the YOUR_API_KEY string with your api key generated on the site!
The free plan of th site you gives only 50 request/month, but with the caching technology (Pager+Room), you just need load the JSON only at the first launchtime!

Description of the app:
In the main screen you can see 24 windows of the Advent Calendar. You can open window on the same day or later, but never before! Every window has a Christmas postcard that you can see clicking on the opened window.

![portrait view](https://dolcenatti.com/advent/screenshotportrait.png)

![landscape view](https://dolcenatti.com/advent/screenshotlandscape.png)

