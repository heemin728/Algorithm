#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#define MAX 26
using namespace std;

int H,W;
char map[MAX][MAX];
bool visited[MAX][MAX];
int dx[4]={1,0,-1,0};
int dy[4]={0,-1,0,1};

bool isInRange(int i, int j){
    return i>=0 && i<H && j>=0 && j<W;
}

char convertDir(int d){
    char directions[] = {'v', '<', '^', '>'};
    if (d>=0 &&d<4) {
        return directions[d];
    }
}

pair<pair<int,int>,int> findStart(){
    for(int i=0;i<H;i++){
        for(int j=0;j<W;j++){
            if(map[i][j]=='.'){
                continue;
            }

            int cnt=0;
            int d;
            for(int k=0;k<4;k++){
                int ni=i+dx[k];
                int nj=j+dy[k];

                if(!isInRange(ni,nj)){
                    continue;
                }

                if(map[ni][nj]=='#'){
                    cnt++;
                    d=k;
                }
            }

            if(cnt <= 1){
                return {{i,j},d};
            }
        }
    }
}

bool keepGoing(int x, int y, int d){

    for(int k=1;k<=2;k++){
        int nx=x+dx[d]*k;
        int ny=y+dy[d]*k;

        if(!isInRange(nx,ny) || visited[nx][ny] || map[nx][ny]=='.'){
            return false;
        }
    }
    return true;
}

void solution(int start_x, int start_y, int start_d){
    queue<pair<int,int>> q;
    int d=start_d;
    for(int i=0;i<H;i++){
        for(int j=0;j<W;j++){
            visited[i][j]=false;
        }
    }

    q.push({start_x,start_y});

    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        q.pop();

        visited[x][y]=true;

        if(keepGoing(x,y,d)){
            cout << "A";
            q.push({x+dx[d]*2, y+dy[d]*2});
            continue;
        }
        int nd=(d+1)%4;
        if(keepGoing(x,y,nd)){
            cout << "RA";
            d=nd;
            q.push({x+dx[d]*2, y+dy[d]*2});
            continue;
        }
        nd=(d+3)%4;
        if(keepGoing(x,y,nd)){
            cout << "LA";
            d=nd;
            q.push({x+dx[d]*2, y+dy[d]*2});
            continue;
        }     
    }
}

int main(int argc, char** argv)
{
    cin >> H >> W;
    for(int i=0;i<H;i++){
        for(int j=0;j<W;j++){
            char c;
            cin >> c;
            map[i][j]=c;
        }
    }

    pair<pair<int,int>,int> start=findStart();
    cout << start.first.first+1 << " " << start.first.second+1 << "\n" << convertDir(start.second) << "\n";
    solution(start.first.first, start.first.second,start.second);
    
    return 0;
}
