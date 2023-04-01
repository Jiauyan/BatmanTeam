#!/bin/bash
VERBOSE=$1
TIMELIMIT=9s
mkfifo iopipe0 iopipe1

if [[ $VERBOSE ]]
then
    timeout $TIMELIMIT <iopipe0 java -classpath solutions histogram/histogram | tee iopipe1 & <iopipe1 python3 tasks/histogram/run.py | tee iopipe0;
else
    timeout $TIMELIMIT <iopipe0 java -classpath solutions histogram/histogram | python3 tasks/histogram/run.py > iopipe0;
fi
PASS=$?

rm iopipe0 iopipe1
exit $PASS
