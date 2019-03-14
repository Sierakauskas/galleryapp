# GalleryApp

####Spring Gallery with Thymeleaf and Zkoss template engines.

Application has three users:

Anonymous user can only view and search images.

User can view, search and upload image. Also he can edit image name and tag.

Admin can do all the actions that user do, also he can delete image.

###How to change database from h2 to mysql?
1.Go to "gallery-bl" directory, find 'pom.xml', comment-out h2 dependency and un-comment mysql dependency.

2.Go to "gallery-ui" directory, find 'application.properties', comment-out #h2_properties, un-comment #mysql_properties. Leave #servlet_cfg un-commented.

3.Go to "gallery-ui-zk" directory, do all the actions as for "gallery-ui".

4.Maven-Lifecycle-compile.
 
