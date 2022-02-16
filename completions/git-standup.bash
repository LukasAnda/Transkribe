#!/usr/bin/env bash
# Command completion for transkribe
# Generated by Clikt

__skip_opt_eq() {
    # this takes advantage of the fact that bash functions can write to local
    # variables in their callers
    (( i = i + 1 ))
    if [[ "${COMP_WORDS[$i]}" == '=' ]]; then
          (( i = i + 1 ))
    fi
}

_git_standup() {
  local i=1
  local in_param=''
  local fixed_arg_names=()
  local vararg_name=''
  local can_parse_options=1

  while [[ ${i} -lt $COMP_CWORD ]]; do
    if [[ ${can_parse_options} -eq 1 ]]; then
      case "${COMP_WORDS[$i]}" in
        --)
          can_parse_options=0
          (( i = i + 1 ));
          continue
          ;;
        --generate-completion)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--generate-completion' || in_param=''
          continue
          ;;
        --author|-a)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--author' || in_param=''
          continue
          ;;
        --branch|-b)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--branch' || in_param=''
          continue
          ;;
        --week-day|-w)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--week-day' || in_param=''
          continue
          ;;
        --depth|-m)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--depth' || in_param=''
          continue
          ;;
        --force-recursion|-F)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--force-recursion' || in_param=''
          continue
          ;;
        --symbolic-links|-L)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -d|--days)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--days' || in_param=''
          continue
          ;;
        -u|--until)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--until' || in_param=''
          continue
          ;;
        -D|--date-format)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--date-format' || in_param=''
          continue
          ;;
        -h|--help)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -g|--gpg-signed|disabled)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -f|--fetch|--no-fetch)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -s|--silence)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -r|--report)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        -c|--diff-stat)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--diff-stat' || in_param=''
          continue
          ;;
        -A|--after)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--after' || in_param=''
          continue
          ;;
        -B|--before)
          __skip_opt_eq
          (( i = i + 1 ))
          [[ ${i} -gt COMP_CWORD ]] && in_param='--before' || in_param=''
          continue
          ;;
        -R|--author-date)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        --verbose)
          __skip_opt_eq
          in_param=''
          continue
          ;;
        --quote)
          __skip_opt_eq
          in_param=''
          continue
          ;;
      esac
    fi
    case "${COMP_WORDS[$i]}" in
      *)
        (( i = i + 1 ))
        # drop the head of the array
        fixed_arg_names=("${fixed_arg_names[@]:1}")
        ;;
    esac
  done
  local word="${COMP_WORDS[$COMP_CWORD]}"
  if [[ "${word}" =~ ^[-d] ]]; then
    COMPREPLY=($(compgen -W '--generate-completion --author -a --branch -b --week-day -w --depth -m --force-recursion -F --symbolic-links -L -d --days -u --until -D --date-format -h --help -g --gpg-signed disabled -f --fetch --no-fetch -s --silence -r --report -c --diff-stat -A --after -B --before -R --author-date --verbose --quote' -- "${word}"))
    return
  fi

  # We're either at an option's value, or the first remaining fixed size
  # arg, or the vararg if there are no fixed args left
  [[ -z "${in_param}" ]] && in_param=${fixed_arg_names[0]}
  [[ -z "${in_param}" ]] && in_param=${vararg_name}

  case "${in_param}" in
    --generate-completion)
      ;;
    --author)
      ;;
    --branch)
      ;;
    --week-day)
      ;;
    --depth)
      ;;
    --force-recursion)
      ;;
    --symbolic-links)
      ;;
    --days)
      ;;
    --until)
      ;;
    --date-format)
      ;;
    --help)
      ;;
    --gpg-signed)
      ;;
    --no-fetch)
      ;;
    --silence)
      ;;
    --report)
      ;;
    --diff-stat)
      ;;
    --after)
      ;;
    --before)
      ;;
    --author-date)
      ;;
    --verbose)
      ;;
    --quote)
      ;;
  esac
}

complete -F _git_standup git-standup
