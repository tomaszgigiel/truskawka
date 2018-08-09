# http://stackoverflow.com/questions/59895/getting-the-source-directory-of-a-bash-script-from-within
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/.. #
# http://stackoverflow.com/questions/11420221/sbt-run-from-outside-the-project-directory
(cd $DIR_PROJECT; lein run-main-simple-dev "https://www.google.pl" "$HOME/_delete_content/delete.txt"; cd -) #
