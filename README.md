presentera.g8
=============

g8 template for making awesome presentations with Presentera

## Running
1. [Install sbt](http://www.scala-sbt.org/release/tutorial/Setup.html).
2. [Install giter8](https://github.com/n8han/giter8#installation).  ([Go here](https://github.com/n8han/conscript#linux-mac-windows) if you are sentenced to Windows)
3. In a command console, `cd` to your code cave.
4. Run `g8 joescii/presentera.g8`
5. Fill in the prompted blanks. 
    * Default values are in the square brackets. 
    * Feel free to them in as you wish, but only mess with the versions if you know what you're doing.
    * Note you will be asked for the `passcode` for presenter mode.  This code is saved in the project in clear text, so think about this if you are pushing this project to GitHub.
6. cd to the newly created directory and do your `git init`, etc.
7. `chmod +x ./presentera`
8. `./presentera`
9. Wait on the internet to download.
10. Aim a good browser at [http://localhost:8080](http://localhost:8080).
11. Press `Page Down` / `Page Up` to navigate through the slides.
12. Press `Enter` back in the console to end the show.

Note that other browsers can tune into the show, with the presenter controlling advancement of the steps.
If the browser has `localhost` as the hostname, it is assumed that user is the presenter.

## Editing
Edit the slides/steps in `src/main/webapp/index.html`.
Doctor up `src/main/webapp/css/impress.css` to your liking.
If you're really feeling inspired, tinker with `src/main/webapp/js/impress.js`.
Comments can be found throughout these files to guide you along.
After all, _Presentera_ is just [_impress.js_](https://github.com/bartaz/impress.js/) with a server backend.

## Heroku
This project is also ready for deployment on [Heroku](https://heroku.com/).
In this deployment, no one can reach the application at `localhost`.
Instead you navigate to `/presenter` on your app and enter the passcode.
You dictated the passcode when you created the project.
However, if you're totally OSS then the passcode is on github for all to see.
In that case, you can override the default by adding `presentera_passcode` as a _Config Variable_ under your Heroku application's _Settings_ tab.

## Collecting screenshots
Often when you give a talk, you will be asked to provide slides.
That would be hard to do if you only have a web application like Presentera.
Fortunately, we've taken advantage of [ScalaTest's Selenium support](http://scalatest.org/user_guide/using_selenium) to automate flipping through the slides and firing screenshots.
Out of the box, Presentera will work if you are using Windows and have Chrome installed.
Just execute `test` in the sbt console.
Once it is done, you will have your screenshots in `target/screenshots`.
Upload them to [smallpdf.com](http://smallpdf.com/jpg-to-pdf) to convert into a pdf document.
Save the pdf document overwriting `src/main/resources/slides.pdf`.
Add a link to `/download` at the end of your presentation to allow your audience to download the slides at the end of the presentation.
