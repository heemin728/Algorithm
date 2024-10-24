#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<deque>
#define MAX 51
using namespace std;

int n;
int board[MAX][MAX];
bool visited[MAX][MAX];
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool checkRange(int i, int j){
    return i>=0 && j>=0 && i<n && j<n;
}

void bfs(){
    deque<pair<pair<int,int>,int>> dq;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            visited[i][j]=false;
        }
    }

    int answer=100000;

    visited[0][0]=true;
    dq.push_front({{0,0},0});

    while(!dq.empty()){
        int x=dq.front().first.first;
        int y=dq.front().first.second;
        int num=dq.front().second;

        dq.pop_front();        

       if(x==n-1 && y==n-1){
            cout << num << "\n";
            return;
        }

        for(int k=0;k<4;k++){
            int nx=x+dx[k];
            int ny=y+dy[k];

            if(!checkRange(nx,ny) || visited[nx][ny]){
                continue;
            }

            visited[nx][ny]=true;

            if(board[nx][ny]==1){
                dq.push_front({{nx,ny},num});
            }
            if(board[nx][ny]==0){
                dq.push_back({{nx,ny},num+1});
            }
        }
    }
    
}

int main(){
    cin >> n;

    for(int i=0;i<n;i++){
        string s;
        cin >> s;

        for(int j=0;j<n;j++){
            board[i][j]=s[j]-'0';
        }
    }
    bfs();
}

