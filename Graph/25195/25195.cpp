#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#define MAX 100001
using namespace std;

int N,M;
int S;
vector<int> v[MAX];
vector<int> fan;
int exist[MAX];

bool bfs(){
    if(exist[1]){
        return false;
    }

    queue<int> q;
    q.push(1);
    exist[1]=true;

    while(!q.empty()){
        int node=q.front();
        q.pop();

        if(v[node].size()==0){
            return true;
        }

        for(int i=0;i<v[node].size();i++){
            int next = v[node][i];
            if(!exist[next]){
                exist[next]=true;
                q.push(next);
            }
        }
    }

    return false;

}

int main(){
    cin >> N >> M;

    for(int i=0;i<M;i++){
        int a,b;
        cin >> a >> b;
        v[a].push_back(b);
    }

    cin >> S;

    for(int i=0;i<S;i++){
        int a;
        cin >> a;
        fan.push_back(a);
        exist[a]=true;
        // if(a==1){
        //     cout << "Yes\n";
        //     return 0;
        // }
    }

    sort(fan.begin(),fan.end());


    bool result = bfs();

    if(result){
        cout << "yes\n";
    }
    else{
        cout << "Yes\n";
    }
}