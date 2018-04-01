//
//  main.cpp
//  merkletree
//
//  Created by Zhicong Huang on 20/03/18.
//  Copyright © 2018 Zhicong Huang. All rights reserved.
//

#include <iostream>
#include <sstream>
#include "merkle_tree.h"

using namespace std;
using namespace merkletree;

void test_merkle_construction();

int main()
{
    test_merkle_construction();
}

vector<string> split(string s, char delim)
{
    vector<string> result;
    stringstream ss(s);
    string item;
    while(getline(ss, item, delim))
    {
        if(item.empty())
            continue;
        result.push_back(item);
    }
    return result;
}

void test_merkle_construction()
{
    unique_ptr<SerialHasher> hasher(new Sha256Hasher());
    MerkleTree tree(move(hasher));
    // Build the tree on the data "Created by Zhicong Huang on 20/03/18", which are separated into a sequence of words by spaces.
    string s("Created by Zhicong Huang on 20/03/18");
    vector<string> data = split(s, ' ');
    for(int i = 0; i < data.size(); i++)
    {
        tree.AddLeaf(data[i]);
    }
    
    cout << "Root: " << tree.CurrentRoot().substr(0, 3) << "..." << endl;
    cout << "Leaf count: " << tree.LeafCount() << endl;
    cout << "Leaf hashes: " << endl;
    for(int i = 0; i < tree.LeafCount(); i++)
    {
        cout << "| " << tree.LeafHash(i+1).substr(0, 3) << "...(" << data[i] << ")";
    }
    cout << endl << endl;
    for(int i = 0; i < tree.LeafCount(); i++)
    {
        cout << "---- Path from (" << data[i] << ") to the root ----" << endl;
        vector<string> path = tree.PathToCurrentRoot(i + 1);
        for(int j = 0; j < path.size(); j++)
        {
            cout << path[j].substr(0, 3) << "...-->";
        }
        cout << endl << endl;
    }
    
}
