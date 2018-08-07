cd %~dp0/../..
md "%HOMEPATH%/_delete_content/"
start lein run-main-jsoup "https://www.google.pl" -o "%HOMEPATH%/_delete_content/delete.txt" -H "localhost" -p "80" -v "false"
pause
