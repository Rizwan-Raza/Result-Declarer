@echo off
color 0a
prompt Rex:$g 
REM runs only if javac command and ojdbc jar is added to the path variable and classpath variable.
set classpath=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar;.;
:label
cls
javac -d . *.java -Xlint
java rex.jfx.result.Result
pause
goto label
