package com.anupam.ds.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UnionFindClient {

    public static void main(String[] args) throws IOException {
        String inputFileName = args[0] + ".txt";
        InputStream in = UnionFindClient.class.getClassLoader().getResourceAsStream(inputFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String row = null;
        int counter = 0;
        int N = 0;
        UnionFind lazyUnionFindBySize = null;
        while( (row = reader.readLine()) != null){
            if (counter == 0 ) {
                N = Integer.parseInt(row);
                lazyUnionFindBySize = new LazyUFByRankAndPC(N);
            }else {
                String[] strArray = row.split(" ");
                int p = Integer.parseInt(strArray[0]);
                int q = Integer.parseInt(strArray[1]);
                if(lazyUnionFindBySize.isConnected(p, q))
                    continue;

                lazyUnionFindBySize.union(p, q);
                System.out.println(p + " " + q);
            }
            counter++;
        }
        System.out.println(lazyUnionFindBySize.count() + " components");
    }
}
