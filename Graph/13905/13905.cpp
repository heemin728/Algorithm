#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;
#define MAX 100001

int N,M;
int s,e;
vector<pair<int,pair<int,int>>> v;
int parent[MAX];
vector<pair<int,int>> nodes[MAX];

bool cmp(pair<int,pair<int,int>> p1, pair<int,pair<int,int>> p2){
    return p1.first > p2.first;
}

int find(int a){
    if(a==parent[a]){
        return a;
    }

    return parent[a]=find(parent[a]);
}

void _union(int a, int b){
    int pa=find(a);
    int pb=find(b);

    if(pa>=pb){
        parent[pa]=pb;
    }
    else{
        parent[pb]=pa;
    }
}

int main(){
    cin >> N >> M;
    cin >> s >> e;

    for(int i=0;i<M;i++){
        int h1,h2,k;
        cin >> h1 >> h2 >> k;
        v.push_back({k,{h1,h2}});
    }

    sort(v.begin(),v.end(),cmp);

    for(int i=1;i<=N;i++){
        parent[i]=i;
    }

    int sum=0;
    int answer=0;

    bool connect[MAX]={false,};

    for(int i=0;i<M;i++){
        int h1=v[i].second.first;
        int h2=v[i].second.second;
        int k=v[i].first;

        if(find(h1)!=find(h2)){
            connect[i]=true;
            _union(h1,h2);
            nodes[h1].push_back({h2,k});
            nodes[h2].push_back({h1,k});

        }
    }

    int visited[MAX];
    for(int i=1;i<=N;i++){
        visited[i]=99999999;
    }

    queue<int> q;
    q.push(s);

    while(!q.empty()){
        int node=q.front();
        q.pop();

        for(int i=0;i<nodes[node].size();i++){
            int next=nodes[node][i].first;
            int weight=nodes[node][i].second;

            if(visited[next]!=99999999){
                continue;
            }

            visited[next]=min(weight,visited[node]);
            q.push(next);
        }
    }
    if(visited[e]==99999999){
        cout << 0 << "\n";
    }
    else{
        cout << visited[e] << "\n";    
    }
}