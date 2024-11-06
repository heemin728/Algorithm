#include<iostream>
#include<queue>
#include<vector>
#include<algorithm>
#define MAX 1001
using namespace std;

int w,h;
char board[MAX][MAX];
int x,y;
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};
int time_cnt[MAX][MAX];
int fire_cnt[MAX][MAX];
vector<pair<int,pair<int,int>>> edges;
bool possible;

bool checkRange(int i, int j){
    return i>=0 && i<h && j>=0 && j<w;
}

void fire(){
    queue<pair<int,pair<int,int>>> q;
    bool visited[MAX][MAX];
    for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
            visited[i][j]=false;

            if(board[i][j]=='*'){
                q.push({0,{i,j}});
                visited[i][j]=true;
            }
        }
    }


    while(!q.empty()){
        int a=q.front().second.first;
        int b=q.front().second.second;
        int d=q.front().first;
        q.pop();

        for(int k=0;k<4;k++){
            int na=a+dx[k];
            int nb=b+dy[k];

            if(!checkRange(na,nb) || visited[na][nb] || board[na][nb]=='#'){
                continue;
            }

            visited[na][nb]=true;
            fire_cnt[na][nb]=d+1;
            q.push({d+1,{na,nb}});
        }
    }
}


void check(){

    bool visited[MAX][MAX];
    for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
            visited[i][j]=false;
        }
    }

    queue<pair<pair<int,int>,int>> q;
    q.push({{x,y},0});
    visited[x][y]=true;

    while(!q.empty()){
        int a=q.front().first.first;
        int b=q.front().first.second;
        int d=q.front().second;

        q.pop();

        for(int k=0;k<4;k++){
            int na=a+dx[k];
            int nb=b+dy[k];

            if(!checkRange(na,nb) || visited[na][nb] || board[na][nb]!='.'){
                continue;
            }

            time_cnt[na][nb]=d+1;
            visited[na][nb]=true;

            q.push({{na,nb},d+1});
        }
    }
}


int main(){
    int t;
    cin >> t;

    for(int T=0;T<t;T++){

        edges.clear();
        possible=false;

        cin >> w >> h;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                cin >> board[i][j];
                if(board[i][j]=='@'){
                    x=i;
                    y=j;
                    time_cnt[i][j]=0;
                }
                else{
                    time_cnt[i][j]=-1;
                    fire_cnt[i][j]=-1;
                }
            }
        }

        check();
        fire();

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if((i==0 || j==0 || i==h-1 || j==w-1) && time_cnt[i][j] != -1){
                    edges.push_back({time_cnt[i][j],{i,j}});
                }
            }
        }

        sort(edges.begin(),edges.end());

        int index=0;

        if(edges.size()==0){
            cout << "IMPOSSIBLE\n";
            continue;
        }

        for(int i=0;i<edges.size();i++){
            int out=edges[i].first;
            int out_x=edges[i].second.first;
            int out_y=edges[i].second.second;

            if(out < fire_cnt[out_x][out_y] || fire_cnt[out_x][out_y]==-1){
                possible=true;
                cout << (out+1) << "\n";
                break;
            }
        }

        if(!possible){
            cout << "IMPOSSIBLE\n";
        }
    }
}