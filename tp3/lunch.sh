echo "#graph\texact"

echo "#test.txt"
java TP3 clust test.txt 7

echo "#as-caida20071105-simple.txt"
java TP3 clust ../snap/as-caida20071105-simple.txt 53381

#echo "#as-skitter-simple.txt"
#java TP3 clust ../snap/as-skitter-simple.txt 11095298

echo "#as20000102-simple.txt"
java TP3 clust ../snap/as20000102-simple.txt 12572

echo "#ca-AstroPh-simple.txt"
java TP3 clust ../snap/ca-AstroPh-simple.txt 198050

echo "#email-Enron-simple.txt"
java TP3 clust ../snap/email-Enron-simple.txt 183831

echo "#roadNet-CA-simple.txt"
java TP3 clust ../snap/roadNet-CA-simple.txt 2766607

echo "#roadNet-TX-simple.txt"
java TP3 clust ../snap/roadNet-TX-simple.txt 1921660

echo "#soc-pokec-relationships-simple.txt"
java TP3 clust ../snap/soc-pokec-relationships-simple.txt 15792906

echo "#web-BerkStan-simple.txt"
java TP3 clust ../snap/web-BerkStan-simple.txt 6649470
