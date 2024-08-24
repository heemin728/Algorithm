#include<iostream>
#include<vector>
using namespace std;

int N,M;
vector<int> v[101];
bool visited[101];
int answer=0;

void dfs(int cnt){

    for(int i=0;i<v[cnt].size();i++){
        int next=v[cnt][i];

        if(visited[next]){
            continue;
        }

        visited[next]=true;
        answer++;
        dfs(next);

    }
}

int main(){
    
    cin >> N >> M;

    for(int i=0;i<M;i++){
        int a,b;
        cin >> a >> b;

        v[a].push_back(b);
        v[b].push_back(a);
    }

    visited[1]=true;
    dfs(1);
    cout << answer << "\n";

}