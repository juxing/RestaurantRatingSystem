//BY MING ZHANG
function submitIt(form) {
   var errCount = 0;
   var errMsg = "";
   //var dateReg = /^\d+$/
   var yearReg = /^\d{4}$/;
   var monthReg = /^\d{1,2}$/;
   var dayReg = /^\d{1,2}$/;
   var checkYear = document.getElementById("Year").value;
   var checkMonth = document.getElementById("Month").value;
   var checkDay = document.getElementById("Day").value;

   if(checkYear == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter year.";
       errCount++;
   }
   else if(!(yearReg.test(checkYear))) {
       errMsg += "\n\n" + (errCount + 1) + ". Yeas is not 4 digits integer.";
       errCount++;
   }

   if(checkMonth == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter month.";
       errCount++;
   }
   else if(!(monthReg.test(checkMonth))) {
       errMsg += "\n\n" + (errCount + 1) + ". Month is not 1 to 2 digits integer.";
       errCount++;
   }
   else if((parseInt(checkMonth) < 1) || (parseInt(checkMonth) > 12)) {     
         errMsg += "\n\n" + (errCount + 1) + ". Month is not integer between 1 and 12.";
         errCount++; 
   }

   if(checkDay == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter day.";
       errCount++;
   }
   else if(!(dayReg.test(checkDay))) {
       errMsg += "\n\n" + (errCount + 1) + ". Day is not 1 to 2 digits integer.";
       errCount++;
   }
   else if((parseInt(checkDay) < 1) || (parseInt(checkDay) > 31)) {     
         errMsg += "\n\n" + (errCount + 1) + ". Day is not integer between 1 and 31.";
         errCount++; 
   }


   var nameReg = /^[a-zA-Z0-9\s]+$/
   var checkName = document.getElementById("Name").value;

   if(checkName == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter Name.";
       errCount++;
   }
   else if(!(nameReg.test(checkName))) {
       errMsg += "\n\n" + (errCount + 1) + ". Name cannot have special chars.";
       errCount++
   }


   if (errCount > 0) {
      alert("Please correct the following " + errCount + " fields" + errMsg);
      return false;
   }
   else {
      return true;
   }
}

function checkDate() {
   var errCount = 0;
   var errMsg = "";
   //var dateReg = /^\d+$/;
   var yearReg = /^\d{4}$/;
   var monthReg = /^\d{1,2}$/;
   var dayReg = /^\d{1,2}$/;
   var checkYear = document.getElementById("Year").value;
   var checkMonth = document.getElementById("Month").value;
   var checkDay = document.getElementById("Day").value;

   if(checkYear == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter year.";
       errCount++;
   }
   else if(!(yearReg.test(checkYear))) {
       errMsg += "\n\n" + (errCount + 1) + ". Yeas is not 4 digits integer.";
       errCount++;
   }

   if(checkMonth == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter month.";
       errCount++;
   }
   else if(!(monthReg.test(checkMonth))) {
       errMsg += "\n\n" + (errCount + 1) + ". Month is not 1 to 2 digits integer.";
       errCount++;
   }
   else if((parseInt(checkMonth) < 1) || (parseInt(checkMonth) > 12)) {     
         errMsg += "\n\n" + (errCount + 1) + ". Month is not integer between 1 and 12.";
         errCount++; 
   }

   if(checkDay == "") {
       errMsg += "\n\n" + (errCount + 1) + ". Please enter day.";
       errCount++;
   }
   else if(!(dayReg.test(checkDay))) {
       errMsg += "\n\n" + (errCount + 1) + ". Day is not 1 to 2 digits integer.";
       errCount++;
   }
   else if((parseInt(checkDay) < 1) || (parseInt(checkDay) > 31)) {     
         errMsg += "\n\n" + (errCount + 1) + ". Day is not integer between 1 and 31.";
         errCount++; 
   }

   if (errCount > 0) {
      alert("Please correct the following " + errCount + " fields" + errMsg);
      return false;
   }
   else {
      alert("Pass the check. Please submit.");
      document.getElementById("Dateoutput").value = checkYear + "-" + checkMonth + "-" + checkDay;
      return true;
   }
}

function checkName() {
   var nameCount = 0;
   var nameMsg = "";
   var nameReg = /^[a-zA-Z0-9\s]+$/
   var checkName = document.getElementById("Name").value;

   if(checkName == "") {
       nameMsg += "\n\n" + 1 + ". Please enter Name.";
       nameCount++;
   }
   else if(!(nameReg.test(checkName))) {
       nameMsg += "\n\n" + 1 + ". Name cannot have special chars.";
       nameCount++
   }

   if (nameCount > 0) {
      alert("Please correct the following " + nameCount + " fields" + nameMsg);
      return false;
   }
   else {
      alert("Pass the check. Please submit.");
      document.getElementById("Nameoutput").value = checkName;
      return true;
   }
}