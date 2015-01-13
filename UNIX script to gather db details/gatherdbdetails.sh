
ora_tab=/etc/oracle/oratab
 
old_path=$PATH
for ORACLE_SID in `ps -aef|grep ora_pmon|grep -v grep|awk '{ print $8 }'|sed 's/ora_pmon_//g'|grep -v sed`
do
 
echo '<br>'
echo '************************************************************************************'
 
echo '<br>'
        ORACLE_HOME=`cat $ora_tab|grep $ORACLE_SID|cut -d : -f 2`
        export ORACLE_SID
        export ORACLE_HOME
        export PATH=$ORACLE_HOME/bin:$PATH
 
echo '<br>'
echo 'Connecting to DB ['$ORACLE_SID']'
echo '<br>'
echo 'Using Oracle Home['$ORACLE_HOME']'
 
sqlplus -S -M "HTML ON TABLE 'BORDER="2"'"  / as sysdba <<EOF
set pagesize 500
set linesize 123
select name from v\$database;
 
select name,value from v\$PARAMETER order by name asc;
exit
 
EOF
 
echo '<br>'
echo '************************************************************************************'
 
echo '<br>'
done
