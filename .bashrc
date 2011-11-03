WORKON_HOME=~/Scripts/python/.envs
source /usr/local/bin/virtualenvwrapper.sh > /dev/null

parse_git_branch() {
  git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/(\1)/'
}

PS1='\n[\e[01;32m\u@\h\e[0;0m][\w]\n\e[1;37m$(parse_git_branch)\e[0;0m~> '


