# VVorlds
https://github.com/Eric-Eagan/vvorlds
VVorlds is a web app for file storage, backups, and sharing with a focus on providing support for the designing of fictional worlds.  
Project creates a directory in D drive called VVorldsFiles to store uploaded files. This can be modified in the showStartPage Handler of the HomeController.

## User Stories
### File Storer
As a user with extra files I want to be able to store them remotely, so that I have easier access across multiple devices and as a backup.  
**Acceptance criteria:** * Recieve file from user * Store file locally * File is accessible to user in future

### File Sharer
As a user with files to share I want to be able to assign other user to files, so that the files are accessible to them as well.  
**Acceptance criteria:** * Recieve target user * Confirm target is valid * File is accessible to target in future * Option to 'unshare'

### World Builder
As a user working on building a fictional world I want easier access to helpful resources, so that the process is easier.  
**Aceptance criteria:** * Have selection of resources * Make resources accessible to users

### Account Holder
As a user with an account I want to be able to have control over what is stored there so that if information changes or is no longer relevant it can be updated to be accurate.
**Aceptance criteria:** * Provide access to data of users account * Data that is mutable can be updated by user * User can delete account

### Admin 
As an admin I want control over resources and the types labels can be called, so that users can be provided more tools without downtime.
**Aceptance criteria:** * Admin can upload new pictures and assign File Type labels to them * Users will be given them alongside default file types when uploading * Admin will be able to add resource title and link to collection of resources * Add resources will be presented alongside default resources

## Technical Challenges
#### Transferring data from Database to Java Objects to Javascript to HTML
 > It's a chain of communication through different formats that don't always communicate happily with each other.  
 > Database to Java we learned early on using Spring Data JPA. Using Entities to map database tables to Java objects felt quite inutitive.  
 > Javascript to HTML was also not bad, came down to learning the Document Object Model (DOM) and then keeping track of which elements were being edited.  
 > The worst was Java to Javascript. Passing the Java objects had to be passed through the model or session to be accessed, but those each have different ways you are able to access them. In addition, certain JSP elements can skip the script file entirely using scriplets to access the required data.  
#### File upload and download at a distance
 > Being the main function of the app, this was also the part that took was more complicated.  
 > Upload has a specific path it follows.  
 > Local file writing itself is somewhat basic, but doing it remotely makes things less straightforward.  
 > HTML has a built in input that accepts a file from the User,  
 > That can be encoded as a MultipartFile for the Spring Framework,  
 > Which is then written to the local storage file.  
 >  
 > Download is done through a different path as it is a get request.  
 > It retrieves the local file normally and then convert it into a Spring framework resource.  
 > This can then be packaged into a ResponseEntity that is returned to the user.
