#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define MAX 100001


int N,R,Q;
vector<int> list[MAX];
bool visited[MAX];
int cnt[MAX];

int dfs(int node, int count){
    
    bool end=true;
    
    for(int i=0;i<list[node].size();i++){
        int next=list[node][i];
        if(visited[next]){
            continue;
        }
        end=false;
        visited[next]=true;

        cnt[node]+=dfs(next,count);
    }

    if(end){
        return cnt[node]=1;
    }
    return ++cnt[node];
}

int main() {
    std::ios_base::sync_with_stdio( false );
    std::cin.tie( NULL );
    std::cout.tie( NULL );

    cin >> N >> R >> Q;

    for(int i=0;i<N-1;i++){
        int u,v;
        cin >> u >> v;
        list[u].push_back(v);
        list[v].push_back(u);
    }

    visited[R]=true;
    dfs(R,0);

    for(int i=0;i<Q;i++){
        int num;
        cin >> num;
        cout << cnt[num] << "\n";
    }
    return 0;
}
