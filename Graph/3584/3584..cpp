#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#define MAX 10001
using namespace std;

int n;
int t1,t2;
vector<int> v[MAX];
bool visited[MAX];

void dfs(int node){

    if(v[node].size()==0){
        //cout << "root\n";
        return;
    }

   for(int i=0;i<v[node].size();i++){
       int parent = v[node][i];

       if(visited[parent]){
            cout << parent << "\n";
            return;
       }

       visited[parent]=true;
       dfs(parent);
   }

}

int main(){
    int t;
    cin >> t;

    for(int T=0;T<t;T++){
        cin >> n;
        for(int i=1;i<=n;i++){
            v[i].clear();
            visited[i]=false;
        }
        

        for(int i=0;i<n-1;i++){
            int a,b;
            cin >> a >> b;
            v[b].push_back(a);
        }

        cin >> t1 >> t2;

        // for(int i=1;i<=n;i++){
        //     cout << i << "-> ";
        //     for(int j=0;j<v[i].size();j++){
        //         cout << v[i][j] << " ";
        //     }
        //     cout << "\n";
        // }

        visited[t1]=true;
        visited[t2]=true;
        
        dfs(t1);
        dfs(t2);

    }
}