#include <iostream>
#include <vector>
#include <queue>
#define MAX 200001
using namespace std;

int N, M;
vector<int> friends[MAX];
int answer[MAX];
int near[MAX];

int main() {

    cin >> N;

    for (int i=1; i<=N; i++) {
        int a;
        while (true) {
            cin >> a;
            if (a == 0) break;
            friends[i].push_back(a);
        }
    }

    for(int i=1;i<=N;i++){
        answer[i]=-1;
    }

    cin >> M;
    queue<int> q1;
    for (int i=0;i<M;i++) {
        int num;
        cin >> num;
        answer[num]=0;
        q1.push(num);
    }

    while (!q1.empty()) {
        int now = q1.front();
        q1.pop();

        for (int j=0;j<friends[now].size();j++) {
            int next=friends[now][j];
            near[next]++;

            int half=(friends[next].size() + 1)/2;

            if (answer[next]==-1 && half <= near[next]) {
                q1.push(next);
                answer[next] = answer[now] + 1;
            }
        }
    }

    for (int i=1;i<=N;i++) {
        cout << answer[i] << " ";
    }
    cout << "\n";
}
