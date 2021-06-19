**IUPLOAD** 

_A NAIVE IMPLEMENTATION OF SHOPIFY PRODUCTS AND IMAGE AUTOMATED UPLOAD_


This is not a very detailed documentation here's what you should know about this project:

* A maven project with not so many dependencies to make you go naughts


* Packaged with maven-assembly, to accommodate the dependencies used in the project during packaging


* Must start the application with arguments:

    * _A path to a txt or csv file containing the product information you want to upload_

    * _A boolean (true or false) to tell if you want to do an upload and generate an output file, or just generate an output file without uploading_
    

* In same directory where the information to the stock you wish to upload reside, place an `api.txt` file there. This should contain the following in this order:
    * `key=YOUR-APP-API-KEY` _e.g key=ertyuiuytrfghjjtfghjkjhgfg_
    * `pass=YOUR-APP-API-PASS` _e.g pass=34567898tyhgfgh876_
    * `store=YOUR-STORE-URLS` _e.g store=omes-beauty-mart.myshopify.com/admin/api/2021-04_
  
  **NB: Store must end with `/admin/api/2021-04`**


* In same directory, place images of products you wish to upload, every image should end with line numbers to the stock they point from the stock upload file.


* If you intend to start the jar file, then run `java -jar iupload.jar "/path-to-file" true`


Alright, that's enough info already, hit me up for more info christopher.eteka.200986@unn.edu.ng or https://www.linkedin.com/in/eteka-christopher-b5ba71138/