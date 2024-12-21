#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#define MAX 31
using namespace std;

int N,M;
int before[MAX][MAX];
int after[MAX][MAX];
vector<pair<int,int>> diff;
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool isInRange(int i, int j){
    return i>=0 && j>=0 && i<N && j<M;
}

int main(){
    cin >> N >> M;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin >> before[i][j];
        }
    }
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin >> after[i][j];
            if(before[i][j]!=after[i][j]){
                diff.push_back({i,j});
            }
        }
    }

    sort(diff.begin(),diff.end());

    queue<pair<int,int>> q;
    vector<pair<int,int>> v;

    bool visited[MAX][MAX]={false,};

    if(diff.size()==0){
        cout << "YES\n";
        return 0;
    }
    q.push(diff[0]);
    v.push_back(diff[0]);
    visited[diff[0].first][diff[0].second]=true;
    
    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        int num=before[x][y];
        q.pop();

        for(int k=0;k<4;k++){
            int nx=x+dx[k];
            int ny=y+dy[k];

            if(!isInRange(nx,ny) || visited[nx][ny] || before[nx][ny]!=num){
                continue;
            }

            v.push_back({nx,ny});
            q.push({nx,ny});
            visited[nx][ny]=true;
        }
    }

    if(diff.size() != v.size()){
        cout << "NO\n";
        return 0;
    }   

    int anum=after[diff[0].first][diff[0].second];
 
    sort(v.begin(),v.end());
    for(int i=0;i<v.size();i++){
        if(v[i].first != diff[i].first || v[i].second != diff[i].second || after[v[i].first][v[i].second]!=anum){
            cout <<"NO\n";
            return 0;
        }
    }
    cout << "YES\n";
}