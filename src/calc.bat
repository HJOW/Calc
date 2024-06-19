@echo off
IF EXIST Index.class goto :RUN
goto :END

:RUN
java Index gui
goto :END

:END