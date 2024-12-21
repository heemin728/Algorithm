#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#include<limits.h>
#define MAX 51
using namespace std;

int N,M;
int map[MAX][MAX];
vector<pair<int,int>> virus;
int total_virus;
int selected_virus[MAX];
int answer=INT_MAX;
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool checkRange(int i, int j){
    return i>=0 && j>=0 && i<N && j<N;
}

void calculate(){
    // 맵 복사
    int c_map[MAX][MAX];
    int total_empty=N*N;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(map[i][j]==1){
                c_map[i][j]=1;
                total_empty--;
            }
            else{
                c_map[i][j]=0;
            }
        }
    }
    
    queue<pair<int,int>> q;
    bool visited[MAX][MAX] = {false, };

    int time = 0;

    for(int i=0;i<M;i++){
        int x=virus[selected_virus[i]].first;
        int y=virus[selected_virus[i]].second;
        total_empty--;
        q.push({x,y});
        visited[x][y]=true;
    }

    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        int d=c_map[x][y];
        q.pop();

        for(int k=0;k<4;k++){
            int nx=x+dx[k];
            int ny=y+dy[k];

            if(!checkRange(nx,ny) || visited[nx][ny] || c_map[nx][ny]==1){
                continue;
            }

            c_map[nx][ny]=d+1;
            q.push({nx,ny});
            visited[nx][ny]=true;
            total_empty--;
            time=max(time,d+1);
        }
    }

  // 불가한 경우
    if(total_empty != 0){
        if(answer==-1 || answer==INT_MAX){
            answer=-1;
        }
        return;
    }
        if(answer == -1){ 
            answer = time;
        }
        else{
            answer = min(time,answer);
        }
}

void combination(int cnt, int start){
    if(cnt == M){
        calculate();
    }

    for(int i=start;i<total_virus;i++){    
        selected_virus[cnt]=i;
        combination(cnt+1,i+1);
    }
}


int main(){
    cin >> N >> M;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cin >> map[i][j];

            if(map[i][j]==2){
                virus.push_back({i,j});
            }
        }
    }

    total_virus=virus.size();

    combination(0,0);
    cout << answer << "\n";
}