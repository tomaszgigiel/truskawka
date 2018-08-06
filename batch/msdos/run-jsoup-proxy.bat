cd %~dp0/../../..
rmdir /s /q target
md "%HOMEPATH%/_delete_content/"
start lein run-main-jsoup "https://www.google.pl" -o "%HOMEPATH%/_delete_content/delete.txt" -H "localhost" -p "80"
pause
