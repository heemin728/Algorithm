#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 1001
using namespace std;

int n;
int board[MAX][MAX];

int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool checkRange(int i, int j){
    return i>=0 && j>=0 && i<n && j<n;
}

bool possible(int mid){
    queue<pair<int,int>> q;

    bool visited[MAX][MAX];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            visited[i][j]=false;
        }
    }

    q.push({0,0});
    visited[0][0]=true;

    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        q.pop();

        if(x==n-1 && y==n-1){
            return true;
        }

        for(int k=0;k<4;k++){
            int nx=x+dx[k];
            int ny=y+dy[k];

            if(!checkRange(nx,ny) || visited[nx][ny] || abs(board[x][y]-board[nx][ny]) > mid){
                continue;
            }

            q.push({nx,ny});
            visited[nx][ny]=true;
        }
    }
    return false;
}

int main(){
    cin >> n;

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin >> board[i][j];
        }
    }

    int start=0;
    int end=1000000000;
    int mid=0;

    int answer=0;

    while(start <= end){
        mid=(start+end)/2;

        if(possible(mid)){
            answer=mid;
            end=mid-1;
        }
        else{
            start=mid+1;
        }
    }
    cout << answer << "\n";
}