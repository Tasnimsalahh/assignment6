# Assignment 6 
This program takes input arxml file and sorts it alphabetically by SHORT-NAME textfield 

## Example input 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<AUTOSAR>
 <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68a">
 <SHORT-NAME>ContainerB</SHORT-NAME>
 <LONG-NAME>AA</LONG-NAME>
 </CONTAINER>
 <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68b">
 <SHORT-NAME>ContainerA</SHORT-NAME>
 <LONG-NAME>BB</LONG-NAME>
 </CONTAINER>
 <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68c">
 <SHORT-NAME>ContainerC</SHORT-NAME>
 <LONG-NAME>CC</LONG-NAME>
 </CONTAINER>
</AUTOSAR>
```

## Example output

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<AUTOSAR>
    <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68b">
         
        <SHORT-NAME>ContainerA</SHORT-NAME>
         
        <LONG-NAME>BB</LONG-NAME>
         
    </CONTAINER>
    <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68a">
         
        <SHORT-NAME>ContainerB</SHORT-NAME>
         
        <LONG-NAME>AA</LONG-NAME>
         
    </CONTAINER>
    <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68c">
         
        <SHORT-NAME>ContainerC</SHORT-NAME>
         
        <LONG-NAME>CC</LONG-NAME>
         
    </CONTAINER>
</AUTOSAR>
```
